import React from 'react';
import {Redirect} from 'react-router-dom';
import {connect} from 'react-redux';

let mapStateToPropsForRedirect = (state) => ({
    isLoggedIn: state.auth.isLoggedIn
});

export const withAuthRedirect = (Component) => {

    class RedirectComponent extends React.Component {
        render() {
            if (!this.props.isLoggedIn) return <Redirect to='/login' />
            return <Component {...this.props}/>
        }
    }

    return connect(mapStateToPropsForRedirect)(RedirectComponent);

};