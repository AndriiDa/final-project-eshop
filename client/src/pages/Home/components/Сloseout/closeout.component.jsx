import React from "react";
import Carousel from "../../../../modules/Carousel/carousel.component";
import ItemCloseoutComponent from "./temlate.component";

const CloseoutComponent = () => {
  const hardcodeData = [
    {
      id: 1,
      name: "Brown Brim",
      imageUrl: "https://i.ibb.co/ZYW3VTp/brown-brim.png",
      price: 25
    },
    {
      id: 2,
      name: "Blue Beanie",
      imageUrl: "https://i.ibb.co/ypkgK0X/blue-beanie.png",
      price: 18
    },
    {
      id: 3,
      name: "Brown Cowboy",
      imageUrl: "https://i.ibb.co/QdJwgmp/brown-cowboy.png",
      price: 35
    }
  ];
  return (
    <>
      <Carousel
        carouselItems={hardcodeData}
        innerComponent={ItemCloseoutComponent}
      />
    </>
  );
};

export default CloseoutComponent;
