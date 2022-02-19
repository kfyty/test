function encrypt(word) {
    var key = CryptoJS.enc.Utf8.parse("plokIJNUHBygvCFT");
    var iv = CryptoJS.enc.Utf8.parse('qazXSWedcVFRtyui');
    var srcs = CryptoJS.enc.Utf8.parse(word);
    var encrypted = CryptoJS.AES.encrypt(srcs, key, {
        iv: iv,
        mode: CryptoJS.mode.CBC,
        padding: CryptoJS.pad.Pkcs7
    });
    return encrypted.toString();
}

function decrypt(word) {
    var key = CryptoJS.enc.Utf8.parse("plokIJNUHBygvCFT");
    var iv = CryptoJS.enc.Utf8.parse('qazXSWedcVFRtyui');
    var decrypt = CryptoJS.AES.decrypt(word, key, {iv: iv, mode: CryptoJS.mode.CBC, padding: CryptoJS.pad.Pkcs7});
    var decryptedStr = decrypt.toString(CryptoJS.enc.Utf8);
    return decryptedStr.toString();
}
