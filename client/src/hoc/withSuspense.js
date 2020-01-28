import React from 'react';
import LoadingIndicator from '../components/common/LoadingIndicator/LoadingIndicator';

export const withSuspense = (Component) => {
    return (props) => {
        return <React.Suspense fallback={LoadingIndicator} >
            <Component {...props} />
        </React.Suspense>
    };
}