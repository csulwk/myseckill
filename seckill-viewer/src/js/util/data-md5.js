import md5 from "js-md5"
export function encryptWithSalt(password) {
    var salt = "springboot"
    return md5(password + salt)
}
