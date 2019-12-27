import React from "react";
import Carousel from "../../../modules/Carousel/carousel";
import ItemCloseoutComponent from "./temlate.component";
import styles from './closeout.module.scss';

const Closeout = () => {
  const hardcodeData = [
    {
      id: 1,
      name: "Canon EOS 5D Mark IV",
      imageUrl: "https://static.bhphoto.com/images/images500x500/canon_eos_5d_mark_iv_1577130087_1484760.jpg",
      price: 1000
    },
    {
      id: 2,
      name: "Sony Alpha a7R IV",
      imageUrl: "https://static.bhphoto.com/images/images500x500/sony_ilce7rm4_b_alpha_a7r_iv_mirrorless_1563273968_1494679.jpg",
      price: 18
    },
    {
      id: 3,
      name: "Nikon Z 6",
      imageUrl: "https://static.bhphoto.com/images/images500x500/1575632370_1454249.jpg",
      price: 35
    }
  ];
  return (
    <div className={styles.closeout}>
      <Carousel
        carouselItems={hardcodeData}
        innerComponent={ItemCloseoutComponent}
      />
    </div>
  );
};

export default Closeout;
