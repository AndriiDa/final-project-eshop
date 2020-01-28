import React, {useState} from 'react';
import styles from './Paginator.module.scss';
import cn from 'classnames';

let Paginator = ({totalItemsCount, pageSize, currentPage, onPageChanged, subRangeSize = 10}) => {

    let pagesCount = Math.ceil(totalItemsCount / pageSize);

    let pages = [];
    for (let i = 1; i <= pagesCount; i++) {
        pages.push(i);
    }

    let subRangeCount = Math.ceil(pagesCount / subRangeSize);
    let [subRangeNumber, setPortionNumber] = useState(1);
    let leftPortionPageNumber = (subRangeNumber - 1) * subRangeSize + 1;
    let rightPortionPageNumber = subRangeNumber * subRangeSize;


    return <div className={styles.paginator}>
        { subRangeNumber > 1 &&
        <button onClick={() => { setPortionNumber(subRangeNumber - 1) }}>PREV</button> }

            {pages
                .filter(p => p >= leftPortionPageNumber && p<=rightPortionPageNumber)
                .map((p) => {
                return <span className={ cn({
                    [styles.selectedPage]: currentPage === p
                }, styles.pageNumber) }
                             key={p}
                             onClick={(e) => {
                                 onPageChanged(p);
                             }}>{p}</span>
            })}
        { subRangeCount > subRangeNumber &&
            <button onClick={() => { setPortionNumber(subRangeNumber + 1) }}>NEXT</button> }


    </div>
}

export default Paginator;