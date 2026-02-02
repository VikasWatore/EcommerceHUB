// Import Swiper React components
import { Swiper, SwiperSlide } from "swiper/react";

// Import Swiper styles
import 'swiper/css';
import { Pagination, EffectCards, EffectFade, Navigation, Autoplay } from "swiper/modules";
import { bannerList } from "../../utils";

const HeroBanner = () => {
  return (
    <div className='py-2 rounded-md'>
      <Swiper
        grabCursor={true}
        autoplay={{
          delay: 4000,
          disableOnInteraction: false,
        }}
        navigation
        modules={[Pagination, EffectFade,Navigation,Autoplay ]}
        pagination={{clickable:true}}
        scrollbar={{draggable:true}}
        slidesPerView={1}>
            {bannerList.map(
              (item,i)=>{
                <SwiperSlide>
                  <div className={`carousel-item rounded-md sm:h-[500px] h-96 ${color[i]}`}>

                  </div>
                </SwiperSlide>
              }
            )}
      </Swiper>
    </div>
  )
}
export default HeroBanner;