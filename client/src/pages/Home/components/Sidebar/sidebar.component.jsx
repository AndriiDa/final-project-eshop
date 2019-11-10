import React from 'react'
import { Map } from "react-lodash";

import CategoryItemComponent from "../../../../stateless/CategoryItem/category-item.component";

import './sidebar.styles.scss';

const SidebarComponent = ({ elements }) => {
  return (
    <div className="nav-side-menu">
      <h2>Portal menu</h2>
      <ul>
        <Map collection={ elements } iteratee={
          elem => <CategoryItemComponent key={ elem.id } categoryName={ elem }/> }
        />
      </ul>
    </div>
  )
};

export default SidebarComponent;
