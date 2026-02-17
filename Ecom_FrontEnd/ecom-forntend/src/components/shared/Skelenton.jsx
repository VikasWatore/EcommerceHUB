import React from 'react'
import Box from '@mui/material/Box';
import Skeleton from '@mui/material/Skeleton';

const Skelenton = () => {
  return (
    <>
      <Skeleton />
      <Skeleton animation="wave" />
      <Skeleton animation={false} />
    </>

  );
}

export default Skelenton; 