var formatDate = function (date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    minute = minute < 10 ? ('0' + minute) : minute;
    var second = date.getSeconds();
    second = second < 10 ? ('0' + second) : second;
    return y + '-' + m + '-' + d + '-' + h + '-' + minute + '-' + second;
};

const addroad = {
    state() {
        return {
            sectionId: "",
            lng: "",
            lat: "",
            name: "",
            type: "",
            direction: "",
            date: "",
            detectDirection: ""
        }
    },
    mutations: {
        setSelectedSection(state, section) {
            state.sectionId = section.id
            state.lng = section.lng
            state.lat = section.lat
            state.name = section.name
            state.type = section.type
        },
        setDirection(state, direction) {
            state.direction = direction
        },
        setDate(state, date) {
            state.date = formatDate(date)
        },
        setDetectDirection(state, detectDirection) {
            state.detectDirection = detectDirection
        }
    },
}
export default addroad