import React from 'react';
import PopupClasses from "./popUp.module.scss";
import {closePopup} from "../../store/actions/popup.action";
import {connect} from "react-redux";
import CloseIcon from "../../UI/CloseIcon/CloseIcon";

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
        itemValue: store.popup.value,
    }
};

const mapDispatchToProps = dispatch => {
    return {
        closePopup: () => dispatch(closePopup())
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(Popup);
