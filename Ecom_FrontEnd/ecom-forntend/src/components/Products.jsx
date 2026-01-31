import { useEffect } from 'react';
import ProductCard from './ProductCard';
import { FaExclamationTriangle } from 'react-icons/fa';
import { useDispatch, useSelector } from 'react-redux';
import { fetchProducts } from '../store/actions';

const Products = () => {
  const {isLoading,errorMessage}=useSelector(
    (state)=>state.errors
  );
  
  const { products } = useSelector(
    (state) => state.products
  )
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(fetchProducts())
  }, [dispatch])


  // const products = [
  //   {
  //     "productId": 652,
  //     "productName": "iPhone Xs Max",
  //     "image": "https://placehold.co/600x400",
  //     "description": "Experience the latest in mobile technology with advanced cameras, powerful processing, and an all-day battery.",
  //     "price": 1500.0,
  //     "quantity": 25,
  //     "discount": 10.0,
  //     "specialPrice": 1305.0
  //   },
  //   {
  //     "productId": 654,
  //     "productName": "MacBook Air M2s",
  //     "image": "https://placehold.co/600x400",
  //     "description": "Ultra-thin laptop with Apple's M2 chip, providing fast performance in a lightweight, portable design.",
  //     "price": 2500.0,
  //     "quantity": 0,
  //     "discount": 20.0,
  //     "specialPrice": 2400.0
  //   }
  // ];


  return (
    <div className='lg:px-14 sm:px-8 px-4 py-14 2xl:w-[90%] 2xl:mx-auto'>
      {
        isLoading ? (
          <p>it is loading...</p>
        ) : errorMessage ? (
          <div className='flex justify-center items-center h-50'>
            <FaExclamationTriangle className='text-slate-800 text-3xl mr-2' />
            <span className='text-slate-800 text-lg font-medium '>
              {errorMessage}
            </span>
          </div>
        ) : (
          <div className='min-h-175'>
            <div className='pb-6 pt-14 grid 2xl:grid-cols-4 lg:grid-cols-3 sm:grid-cols-2 gap-y-6 gap-x-6'>
              {products &&
                products.map((item, i) => <ProductCard key={i} {...item} />)}
            </div>

          </div>
        )}
    </div>
  )
}

export default Products