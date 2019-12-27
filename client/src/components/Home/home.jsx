import React, {useEffect} from "react";
// import PropTypes from "prop-types";

// import {connect} from "react-redux";
// import {fetchCategoryStart} from "../../pages/Home/state/category/category.actions";
import Closeout from "./Сloseout/closeout";
import CommentsContainer from "./CommentsContainer/commentsContainer";

import s from './home.module.scss';

// const Home = ({comments, newCommentText, dispatch, categoryItems, fetchCategoryStarted}) => {
const Home = (props) => {
    useEffect(() => {
        //fetchCategoryStarted();
        // eslint-disable-next-line
    }, []);
    return (
        //<div className="d-flex">
        <div className={s.home}>
            <Closeout/>
            <CommentsContainer/>
            {/*<SidebarComponent categoryItems={categoryItems.length && categoryItems}/>*/}
            {/*<div className="flex-grow-1">*/}

            {/*</div>*/}
        </div>
    );
};

export default Home;

// Home.defaultProps = {
//     categoryItems: []
// };
//
// Home.propTypes = {
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
// export default connect(mapStateToProps, mapDispatchToProps)(Home);
