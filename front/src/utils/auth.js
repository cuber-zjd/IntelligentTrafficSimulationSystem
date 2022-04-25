const TokenKey = 'my_token'

export function getToken() {
    return window.localStorage.getItem("my_token")
}

export function setToken(token) {
    window.localStorage.setItem(TokenKey, token)
}

export function removeToken() {
    window.localStorage.removeItem(TokenKey)
}
