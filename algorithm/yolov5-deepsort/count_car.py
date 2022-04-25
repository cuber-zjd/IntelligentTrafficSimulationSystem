
# coding=UTF-8
import sys

import numpy as np

import objtracker
from objdetector import Detector
import cv2
from car import Car



if __name__ == '__main__':

    VIDEO_PATH=sys.argv[2]
    # 根据视频尺寸，填充供撞线计算使用的polygon
    # 1364,768
    width = 1364
    height = 768
    mask_image_temp = np.zeros((height, width), dtype=np.uint8)
    # 获取检测参数
    linePosx=float(sys.argv[3])*13.64
    linePosy=float(sys.argv[4])*7.68
    lineLength=float(sys.argv[5])*13.64
    twoLineDistant=float(sys.argv[6])*2

    if linePosx+lineLength>1364:
        lineLength=1364-linePosx
    if linePosy+40+twoLineDistant>768:
        linePosy=768-40-twoLineDistant
    distant=float(sys.argv[7])

    # 填充第一个撞线polygon（蓝色）
    list_pts_blue = [[linePosx, linePosy], [linePosx+lineLength, linePosy], [linePosx+lineLength, linePosy+20], [linePosx, linePosy+20]]
    # list_pts_blue = [[204, 305], [1200, 305], [1200, 325], [204, 325]]

    ndarray_pts_blue = np.array(list_pts_blue, np.int32)
    polygon_blue_value_1 = cv2.fillPoly(mask_image_temp, [ndarray_pts_blue], color=1)
    polygon_blue_value_1 = polygon_blue_value_1[:, :, np.newaxis]

    # 填充第二个撞线polygon（黄色）
    mask_image_temp = np.zeros((height, width), dtype=np.uint8)
    list_pts_yellow = [[linePosx, linePosy+20+twoLineDistant],
                       [linePosx+lineLength, linePosy+20+twoLineDistant],
                       [linePosx+lineLength, linePosy+40+twoLineDistant],
                       [linePosx, linePosy+40+twoLineDistant]]
    # list_pts_yellow = [[204, 385], [1200, 385], [1200, 405], [204, 405]]
    ndarray_pts_yellow = np.array(list_pts_yellow, np.int32)
    polygon_yellow_value_2 = cv2.fillPoly(mask_image_temp, [ndarray_pts_yellow], color=2)
    polygon_yellow_value_2 = polygon_yellow_value_2[:, :, np.newaxis]

    # 撞线检测用的mask，包含2个polygon，（值范围 0、1、2），供撞线计算使用
    polygon_mask_blue_and_yellow = polygon_blue_value_1 + polygon_yellow_value_2

    # 缩小尺寸，1364x768->682x384
    polygon_mask_blue_and_yellow = cv2.resize(polygon_mask_blue_and_yellow, (width//2, height//2))

    # 蓝 色盘 b,g,r
    blue_color_plate = [255, 0, 0]
    # 蓝 polygon图片
    blue_image = np.array(polygon_blue_value_1 * blue_color_plate, np.uint8)

    # 黄 色盘
    yellow_color_plate = [0, 255, 255]
    # 黄 polygon图片
    yellow_image = np.array(polygon_yellow_value_2 * yellow_color_plate, np.uint8)

    # 彩色图片（值范围 0-255）
    color_polygons_image = blue_image + yellow_image
    
    # 缩小尺寸 
    color_polygons_image = cv2.resize(color_polygons_image, (width//2, height//2))

    # list 与蓝色polygon重叠
    list_overlapping_blue_polygon = []

    # list 与黄色polygon重叠
    list_overlapping_yellow_polygon = []

    # 下行数量
    # down_count = 0
    # 上行数量
    # up_count = 0

    font_draw_number = cv2.FONT_HERSHEY_SIMPLEX
    draw_text_postion = (int((width/2.0) * 0.01), int((height/2.0) * 0.05))

    # 实例化yolov5检测器
    detector = Detector()

    # 打开视频
    capture = cv2.VideoCapture(VIDEO_PATH)

    #时间参数
    idx = 0
    fps = capture.get(5)

    divided = 0
    time = 0

    first_time = 0
    second_time = 0

    # 车辆数量
    count=0

    direction=sys.argv[1]

    cars=[]
    while True:
        # 读取每帧图片
        _, im = capture.read()
        if im is None:
            break

        # 缩小尺寸
        im = cv2.resize(im, (width//2, height//2))

        list_bboxs = []
        # 更新跟踪器
        output_image_frame, list_bboxs = objtracker.update(detector, im)
        # 输出图片
        output_image_frame = cv2.add(output_image_frame, color_polygons_image)

        if len(list_bboxs) > 0:

            idx += 1
            divided = idx % int(fps)

            if divided == 0:
                time += 1



            # ----------------------判断撞线----------------------
            for item_bbox in list_bboxs:


                x1, y1, x2, y2, _, track_id = item_bbox
                # 撞线检测点，(x1，y1)，y方向偏移比例 0.0~1.0
                y1_offset = int(y1 + ((y2 - y1) * 0.6))
                # 撞线的点
                y = y1_offset
                x = x1
                if direction=="2":

                    if polygon_mask_blue_and_yellow[y, x] == 1:
                        # 如果撞 蓝polygon
                        if track_id not in list_overlapping_blue_polygon:
                            list_overlapping_blue_polygon.append(track_id)
                            first_time = time + (divided / fps)
                            car=Car(track_id,first_time)
                            cars.append(car)


                    elif polygon_mask_blue_and_yellow[y, x] == 2:
                        # 有此 track_id，则 认为是 DOWN（下行）方向
                        if track_id in list_overlapping_blue_polygon:
                            # 下行+1
                            second_time = time + (divided / fps)
                            for index in range(len(cars)):
                                if cars[index].id==track_id:
                                    cars[index].secondTime=second_time

                            # 删除 蓝polygon list 中的此id
                            list_overlapping_blue_polygon.remove(track_id)
                elif direction == "1":
                    if polygon_mask_blue_and_yellow[y, x] == 1:
                        # 判断 黄polygon list里是否有此 track_id
                        # 有此track_id，则认为是 UP (上行)方向
                        if track_id in list_overlapping_yellow_polygon:
                            # 上行+1
                            second_time = time + (divided / fps)
                            for index in range(len(cars)):
                                if cars[index].id==track_id:
                                    cars[index].secondTime=second_time

                            # 删除 黄polygon list 中的此id
                            list_overlapping_yellow_polygon.remove(track_id)

                    elif polygon_mask_blue_and_yellow[y, x] == 2:
                        # 如果撞 黄polygon
                        if track_id not in list_overlapping_yellow_polygon:
                            list_overlapping_yellow_polygon.append(track_id)
                            first_time = time + (divided / fps)
                            car = Car(track_id, first_time)
                            cars.append(car)

            # ----------------------清除无用id----------------------
            list_overlapping_all = list_overlapping_yellow_polygon + list_overlapping_blue_polygon
            for id1 in list_overlapping_all:
                is_found = False
                for _, _, _, _, _, bbox_id in list_bboxs:
                    if bbox_id == id1:
                        is_found = True
                if not is_found:
                    # 如果没找到，删除id
                    if id1 in list_overlapping_yellow_polygon:
                        list_overlapping_yellow_polygon.remove(id1)

                    if id1 in list_overlapping_blue_polygon:
                        list_overlapping_blue_polygon.remove(id1)
            list_overlapping_all.clear()
            # 清空list
            list_bboxs.clear()

        else:
            # 如果图像中没有任何的bbox，则清空list
            list_overlapping_blue_polygon.clear()
            list_overlapping_yellow_polygon.clear()

        # 输出计数信息
        text_draw = 'detect direction: ' + ("up" if direction==1 else "down")

        output_image_frame = cv2.putText(img=output_image_frame,text=text_draw,
                                         org=draw_text_postion,
                                         fontFace=font_draw_number,
                                         fontScale=0.75, color=(0, 0, 255), thickness=2)
        cv2.imshow('Counting Demo', output_image_frame)
        cv2.waitKey(1)
    print("-----------fps:", fps)
    for car in cars:
        if car.secondTime-car.firstTime!=0 and car.secondTime!=0:
            print("car",car.id,distant/(car.secondTime-car.firstTime),car.firstTime,car.secondTime);
    if capture.isOpened():
        rate=capture.get(5)
        frame_num=capture.get(7)
        duration=frame_num/rate
        print("duration",duration)
    capture.release()
    cv2.destroyAllWindows()
