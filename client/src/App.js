// React components
import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import HomeComponent from "./pages/Home/home.component";
// Redux
import { Provider } from 'react-redux';
import { store } from "./redux/store";
// styles
import './App.scss';

const App = () => {
    return (
        <Provider store={store}>
            <Router>
                <Switch>
                    <Route exact path="/" component={HomeComponent}/>
                    <Route render={() => <div></div>}/>
                </Switch>
            </Router>
        </Provider>
    );
};

export default App;
