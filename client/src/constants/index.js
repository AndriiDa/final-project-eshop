export const HEROKU_APP_URL = 'https://fs7-eshop.herokuapp.com';
export const API_BASE_URL = HEROKU_APP_URL || process.env.REACT_APP_API_BASE_URL || 'http://localhost:9000/api/v1/';
