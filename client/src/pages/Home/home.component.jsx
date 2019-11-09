import React, { useEffect, Fragment } from 'react';
import PropTypes from 'prop-types';
import { FlexContainer } from "../../stateless/FlexContainer/flex-container.styles";

import { connect } from "react-redux";
import { fetchCategoryStart } from "../../redux/category/category.actions";
import SidebarComponent from "./components/Sidebar/sidebar.component";

const HomeComponent = ({fetchCategoryStarted}) => {
    useEffect(() => {
        fetchCategoryStarted();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    return (
        <Fragment>
            <SidebarComponent/>
            <FlexContainer>Home</FlexContainer>
        </Fragment>
    );
};

HomeComponent.propTypes = {
    fetchCategoryStarted: PropTypes.func.isRequired
};

const mapDispatchToProps = dispatch => ({
    fetchCategoryStarted: () => dispatch(fetchCategoryStart())
});

export default connect(null, mapDispatchToProps)(HomeComponent);
