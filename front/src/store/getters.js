const getters = {
    // sidebar: state => state.app.sidebar,
    // device: state => state.app.device,
    token: state => {
        return state.user.token
    },
    // avatar: state => state.user.avatar,
    name: state => { return state.user.name }
}
export default getters