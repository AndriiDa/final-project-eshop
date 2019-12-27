import React, {useEffect} from "react";
import PropTypes from "prop-types";

import {connect} from "react-redux";
import {fetchCategoryStart} from "../../pages/Home/state/category/category.actions";
import CloseoutComponent from "./Сloseout/closeout.component";
import CommentsContainer from "./CommentsContainer/commentsContainer";

import s from './home.module.scss';

// const HomeComponent = ({comments, newCommentText, dispatch, categoryItems, fetchCategoryStarted}) => {
const HomeComponent = (props) => {
    useEffect(() => {
        //fetchCategoryStarted();
        // eslint-disable-next-line
    }, []);
    return (
        //<div className="d-flex">
        <div className={s.home}>
            <CloseoutComponent/>
            <CommentsContainer/>
            {/*<SidebarComponent categoryItems={categoryItems.length && categoryItems}/>*/}
            {/*<div className="flex-grow-1">*/}

            {/*</div>*/}
        </div>
    );
};

export default HomeComponent;

// HomeComponent.defaultProps = {
//     categoryItems: []
// };
//
// HomeComponent.propTypes = {
//     fetchCategoryStarted: PropTypes.func.isRequired,
//     categoryItems: PropTypes.instanceOf(Array)
// };
//
// const mapStateToProps = state => ({
//     categoryItems: state.category.categoryItems
// });
//
// const mapDispatchToProps = dispatch => ({
//     fetchCategoryStarted: () => dispatch(fetchCategoryStart())
// });
//
// export default connect(mapStateToProps, mapDispatchToProps)(HomeComponent);
