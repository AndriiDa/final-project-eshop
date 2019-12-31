import {API_BASE_URL} from '../constants';
import * as axios from "axios";

const instance = axios.create({
    //withCredentials: true,
    baseURL: API_BASE_URL,
    headers: {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTc3NjQ0NDgyLCJleHAiOjE1NzgyNDkyODJ9.PKWADEvW6U-PlWtNuZVI2-4kO_iDLKpAAOJuXipFk5kCit5gU4z4t9FSV9oQhX6E1h4IMceFYnQtMmiZbmmiqw'

    }
});

export const cartApi = {
    getCartByLoginName(loginName = 'ivanov') {
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
        return instance.get(`users/me`)
            .then(response => {
                return response.data;
            })
    }
};