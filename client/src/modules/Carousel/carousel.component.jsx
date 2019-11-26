import React from "react";
import PropTypes from "prop-types";

const Carousel = props => {
  const [active, setActive] = React.useState(0);
  const { carouselItems, innerComponent } = props;

  let scrollInterval = null;
  const ItemComponent = innerComponent;

  const style = {
    carousel: {
      position: "relative"
    },
    carouselItem: {
      position: "absolute",
      visibility: "hidden"
    },
    visible: {
      visibility: "visible"
    }
  };

  React.useEffect(() => {
    scrollInterval = setTimeout(() => {
      setActive((active + 1) % carouselItems.length);
    }, 2000);
    return () => clearTimeout(scrollInterval);
  }, []);

  const slider = React.useRef(null);

  const onButtonClick = () => {
    // `current` указывает на смонтированный элемент `input`
    window.console.log(slider.current);
  };

  return (
    <div style={style.carousel} ref={slider} onClick={() => onButtonClick()}>
      {carouselItems.map(({ ...rest }, index) => {
        const activeStyle =
          active === index ? style.visible : style.carouselItem;
        return (
          <ItemComponent key={rest.id} activeStyle={activeStyle} {...rest} />
        );
      })}
    </div>
  );
};

Carousel.defaultProps = {
  carouselItems: [],
  innerComponent: null
};

Carousel.propTypes = {
  carouselItems: PropTypes.instanceOf(Array),
  innerComponent: PropTypes.any
};

export default Carousel;
