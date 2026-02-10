import { DNA, InfinitySpin, ThreeCircles, Triangle, Vortex } from "react-loader-spinner";

const Spinner = () => {
  return (<DNA
    visible={true}
    height="40"
    width="40"
    ariaLabel="dna-loading"
    wrapperStyle={{}}
    wrapperClass="dna-wrapper"
  />)
}

export default Spinner;