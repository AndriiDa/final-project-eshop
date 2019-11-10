import React, { Fragment, useEffect } from 'react';
import PropTypes from 'prop-types';

import { connect } from "react-redux";
import { fetchCategoryStart } from "../../redux/category/category.actions";
import SidebarComponent from "./components/Sidebar/sidebar.component";

const HomeComponent = ({ categoryItems, fetchCategoryStarted }) => {
    useEffect(() => {
        fetchCategoryStarted();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    return (
        <Fragment>
            <SidebarComponent elements={ categoryItems.length && categoryItems }/>
        </Fragment>
    );
};

HomeComponent.propTypes = {
    fetchCategoryStarted: PropTypes.func.isRequired
};

const mapStateToProps = (state) => ({
    categoryItems: state.category.categoryItems
});

const mapDispatchToProps = dispatch => ({
    fetchCategoryStarted: () => dispatch(fetchCategoryStart())
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(HomeComponent);
