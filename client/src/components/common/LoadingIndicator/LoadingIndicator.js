import React from 'react';
import {Spin, Icon} from 'antd';

export default function LoadingIndicator(props) {
    const antIcon = <Icon type="loading-3-quarters" style={{fontSize: 30}} spin/>;
    return (
        <div>
            <Spin indicator={antIcon} style={{display: 'block', textAlign: 'center', marginTop: 30}}/>
        </div>
    );
}