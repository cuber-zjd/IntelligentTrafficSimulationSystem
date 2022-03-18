const addroad = {
    state() {
        return {
            addRoadLng: "",
            addRoadLat: ""
        }
    },
    mutations: {
        setAddRoadLngLat(state, location) {
            state.addRoadLng = location.lng
            state.addRoadLat = location.lat
        }
    }
}
export default addroad