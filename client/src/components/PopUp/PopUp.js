import React from 'react';
import PopupClasses from "./popUp.module.scss";
import {closePopup} from "../../store/actions/popup.action";
import {connect} from "react-redux";
import CloseIcon from "../../UI/CloseIcon/CloseIcon";
import * as signInActions from '../../store/actions/signIn.action'
import * as signUpActions from '../../store/actions/signUp.action'

const Popup = (props) => {
    return (
        <div className={PopupClasses.wrap}>
            <div className={PopupClasses.popup}>
                <div className={PopupClasses['popup-closeIcon']}>
                    <CloseIcon onClick={props.closePopup}/>
                </div>
                {props.content(props)}
            </div>
        </div>
    )
};


const mapStateToProps = store => {
    return {
        registerSuccess: store.signUp.registerSuccess,
        loginSuccess: store.signIn.loginSuccess,
        itemValue: store.popup.value,

    }
};

const mapDispatchToProps = dispatch => {
    return {
        closePopup: () => dispatch(closePopup()),
        userSignIn: (userLoginInfo) => dispatch(signInActions.userSignIn(userLoginInfo)),
        userSignUp: (userRegisterInfo) => dispatch(signUpActions.userSignUp(userRegisterInfo))
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(Popup);
