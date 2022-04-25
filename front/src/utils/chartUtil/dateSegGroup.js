import { detailTime } from '../formatDate'

//按时间分组
export function dateSegGroup(data, seg, start, end) {

    let segDatas = [];

    for (let i = 0; i < (end - start) / (seg * 60 * 1000); i++) {
        const cars = data.filter(item => {
            return item.passTime >= start + i * seg * 60 * 1000 && item.passTime < start + (i + 1) * seg * 60 * 1000
        })
        console
        segDatas.push({
            start: detailTime(start + i * seg * 60 * 1000),
            end: detailTime(start + (i + 1) * seg * 60 * 1000),
            data: cars
        })
    }
    return segDatas;
}
