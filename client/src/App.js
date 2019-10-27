import React, {useEffect} from 'react';
import PropTypes from 'prop-types';
import {connect} from 'react-redux';
import {fetchCategoryStarted} from './redux/category/category.actions';
import './App.scss';

const App = ({fetchCategoryStarted}) => {
    useEffect(() => {
        fetchCategoryStarted();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    return <p>Working!</p>;
};

App.propTypes = {
    fetchCategoryStarted: PropTypes.func.isRequired
};

const mapDispatchToProps = dispatch => ({
    fetchCategoryStarted: () => dispatch(fetchCategoryStarted())
});

export default connect(
    null,
    mapDispatchToProps
)(App);
