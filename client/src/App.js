import React, { useEffect } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { fetchCategoryStart } from './redux/category/category.actions';
import './App.scss';

const App = ({ fetchCategoryStarted }) => {
  useEffect(() => {
    fetchCategoryStarted();
  }, []);

  return <p>Working!</p>;
};

App.propTypes = {
  fetchCategoryStarted: PropTypes.func.isRequired
};

const mapDispatchToProps = dispatch => ({
  fetchCategoryStarted: () => dispatch(fetchCategoryStart())
});

export default connect(
  null,
  mapDispatchToProps
)(App);
