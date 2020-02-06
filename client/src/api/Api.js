import {API_BASE_URL, ACCESS_TOKEN} from '../constants';
import * as axios from "axios";

const instance = axios.create({
    //withCredentials: true,
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
        'Authorization': localStorage.getItem(ACCESS_TOKEN)
            ? 'Bearer ' + localStorage.getItem(ACCESS_TOKEN)
            : undefined
    }
});

export const cartApi = {
    getCartByLoginName(loginName) {
        return instance.get(`carts/${loginName}`)
            .then(
                response => {
                    return response.data;
                })

    },
    existsByUserIdAndProductId(userId, productId) {
        return instance.get(`carts/${userId}/${productId}`)
            .then(
                response => {
                    return response.data;
                })
    },
    addItemToCart(userId = 1, productId = 1) {
        return instance.post(`carts`, {
            'userId': userId,
            'productId': productId,
            'quantity': 1
        })
            .then(
                response => {
                    return (response.data);
                })
    },
    deleteItemFromCart(userId, productId) {
        return instance.delete(`carts/${userId}/${productId}`)
            .then(
                response => {
                    return (response.data);
                });
    }
};

export const productsApi = {
    getProducts(currentPage = 0, pageSize = 5) {
        return instance.get(`products/?page=${currentPage}&size=${pageSize}`)
            .then(response => {
                return response.data;
            })
    },
    getProductById(id = 1) {
        return instance.get(`products/${id}`)
            .then(
                response => {
                    return response.data;

                })
    }
};

export const usersApi = {
    authMe() {
        // if (!localStorage.getItem(ACCESS_TOKEN)) {
        //     return Promise.reject("No access token set.");
        // }
        return instance.get(`users/me`);
    },
    login(usernameOrEmail, password) {
        return instance.post(`auth/signin`, {
            usernameOrEmail,
            password
        });
            // .then(
            //     response => {
            //         localStorage.setItem(ACCESS_TOKEN, response.data.accessToken);
            //         return response.data;
            //     });
    },
    signup(signupRequest) {
        return instance.post(`auth/signup`, {
            ...signupRequest
        })
            .then(
                response => {
                    return (response.data);
                });
    }
};