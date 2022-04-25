export function fileFormatDate(date) {
    const strs = date.split(" ")
    const hms = strs[1].split(":")
    return strs[0] + "-" + hms[0] + "-" + hms[1] + "-" + hms[2]
}

export function detailTime(dateTime) {
    var date = new Date(dateTime);
    var Y = date.getFullYear() + '-'
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
    var D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' '
    var h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':'
    var m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':'
    var s = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds())
    return Y + M + D + h + m + s
}

export function getDateFromString(str) {
    let dateStr = str.replace(/-/g, '/');
    var timeTamp = new Date(dateStr).getTime();
    return timeTamp;
}

