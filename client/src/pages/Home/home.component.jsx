import React, { useEffect } from 'react';
import PropTypes from 'prop-types';
import { FlexContainer } from "../../stateless/FlexContainer/flex-container.styles";
import { connect } from "react-redux";
import { fetchCategoryStart } from "../../redux/category/category.actions";

const HomeComponent = ({fetchCategoryStarted}) => {
    useEffect(() => {
        fetchCategoryStarted();
    }, []);

    return (
        <FlexContainer>Home</FlexContainer>
    );
};

HomeComponent.propTypes = {
    fetchCategoryStarted: PropTypes.func.isRequired
};

const mapDispatchToProps = dispatch => ({
    fetchCategoryStarted: () => dispatch(fetchCategoryStart())
});

export default connect(null, mapDispatchToProps)(HomeComponent);
