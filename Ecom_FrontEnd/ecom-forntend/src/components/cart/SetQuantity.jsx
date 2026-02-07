import React from 'react'

const SetQuantity = ({
  qunatity,
  cardCounter,
  handeQtyIncrease,
  handleQtyDecrease,
}) => {
  return (
    <div className='flex gap-8 items-center'>
      {cardCounter ? null : <div className='font-semibold'></div>}
      <div className='flex md:flex-row flex-col gap-4 items-center lg:text-[22px] text-sm'>
        <button disabled={qunatity <= 1}
          className='border-[1.2px] border-slate-800 px-3 py-1 rounded cursor-pointer'
          onClick={handleQtyDecrease}
        >
          -
        </button>

        <div className='text-red-600'>{qunatity}</div>
        <button
          className='border-[1.2px] border-slate-800 px-3 py-1 rounded  cursor-pointer'
          onClick={handeQtyIncrease}
        >
          +
        </button>
      </div>
    </div>
  )
}

export default SetQuantity;