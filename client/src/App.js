// React components
import React, { lazy, Suspense } from 'react';
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

// import { connect } from 'react-redux';
// import { fetchCategoryStart } from './redux/category/category.actions';
// const App = ({ fetchCategoryStarted }) => {
//   useEffect(() => {
//     fetchCategoryStarted();
//   }, []);
//
//   return <p>Working!</p>;
// };
//
// App.propTypes = {
//   fetchCategoryStarted: PropTypes.func.isRequired
// };
//
// const mapDispatchToProps = dispatch => ({
//   fetchCategoryStarted: () => dispatch(fetchCategoryStart())
// });
//
// export default connect(
//   null,
//   mapDispatchToProps
// )(App);
