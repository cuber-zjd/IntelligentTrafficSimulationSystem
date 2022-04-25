// 统计不同速度区间内的个数
export function speedGroup(data) {
    let Group = {
        '0-20': 0,
        '21-40': 0,
        '41-60': 0,
        '61-80': 0,
        '81-100': 0,
        '101-120': 0,
    }
    data.forEach(item => {
        if (item <= 20) {
            Group['0-20'] += 1
        } else if (item <= 40) {
            Group['21-40'] += 1
        } else if (item <= 60) {
            Group['41-60'] += 1
        } else if (item <= 80) {
            Group['61-80'] += 1
        } else if (item <= 100) {
            Group['81-100'] += 1
        } else if (item <= 120) {
            Group['101-120'] += 1
        }
    })
    if (Group['0-20'] === 0) {
        delete Group['0-20']
    }
    if (Group['21-40'] === 0) {
        delete Group['21-40']
    }
    if (Group['41-60'] === 0) {
        delete Group['41-60']
    }
    if (Group['61-80'] === 0) {
        delete Group['61-80']
    }
    if (Group['81-100'] === 0) {
        delete Group['81-100']
    }
    if (Group['101-120'] === 0) {
        delete Group['101-120']
    }
    return Group
}

export function GroupConversion(Group) {
    let data = []
    for (let key in Group) {
        data.push({
            name: key,
            value: Group[key]
        })
    }
    return data
}
