import base64url from 'base64-url';
import * as Cookies from 'es-cookie';
import {setSeconds} from 'date-fns'


const CookieService = (() => {
    const _setCookie = (name, value, expire = null, path = '/') => {
        Cookies.set(
            base64url.encode(name),
            base64url.encode(JSON.stringify({accessToken: value.accessToken, refreshToken: value.refreshToken, expire: setSeconds(new Date(), value.expireInSeconds)})),
            {
                secure: window.location.protocol === 'https:',
                sameSite: 'strict',
                expires: expire,
                path : path
            });
    };

    const _getCookie = (name) => {
        const allCookies = Cookies.getAll();
        const result = Object.keys(allCookies).filter(item => base64url.decode(item) === name).map(item => allCookies[item])[0];
        if(Boolean(result)){
            return JSON.parse(base64url.decode(result));
        }
    };

    const _deleteCookie = (name, path = '/') => {
        const allCookies = Cookies.getAll();
        const nameCookie = Object.keys(allCookies).filter(item => base64url.decode(item) === name)[0];
        Cookies.remove(nameCookie, { path: path });
    };

    return{
        setCookie: _setCookie,
        getCookie: _getCookie,
        deleteCookie: _deleteCookie,
    }
})();

export default CookieService;
