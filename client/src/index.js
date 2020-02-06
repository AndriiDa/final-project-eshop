import React from 'react';
import ReactDOM from 'react-dom';
import App from './app/App';
import store from './redux/redux-store';
import {BrowserRouter as Router} from 'react-router-dom';
import {Provider} from 'react-redux';

ReactDOM.render(
    <Router>
        <Provider store={store}>
            <App/>
        </Provider>
    </Router>, document.getElementById('root'));

window.store = store;