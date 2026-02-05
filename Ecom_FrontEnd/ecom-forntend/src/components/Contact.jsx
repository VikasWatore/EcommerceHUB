import { FaEnvelope, FaMapMarked, FaMapMarkedAlt, FaPhone } from "react-icons/fa";

const Contact = () => {
  return (
    <div
      className='flex flex-col items-center justify-center min-h-screen py-12 bg-cover bg-center'
      style={{ backgroundImage: "url('https://images.unsplash.com/photo-1534536281715-e28d76689b4d?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D')" }}
    >
      <div className='bg-white shadow-lg rounded-lg p-8 w-full max-w-lg'>
        <h1 className='text-4xl font-bold text-center mb-6 '>Contact us</h1>
        <p className='text-gray-600 text-center mb-4'> we Would love to hear from you ! Please fill out the form below or contact us directly</p>
        <form action="" className='space-y-4'>
          <div>
            <label className='block text-sm font-medium text-gray-700' htmlFor="">Name</label>
            <input type="text" required className='mt-1 w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-blue-500' />
          </div>

          <div>
            <label className='block text-sm font-medium text-gray-700' htmlFor="">Email</label>
            <input type="email" required className='mt-1 w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-blue-500' />
          </div>

          <div>
            <label className='block text-sm font-medium text-gray-700' htmlFor="">Message</label>
            <textarea rows="4" required className='mt-1 w-full border border-gray-300 rounded-lg p-2 focus:outline-none focus:ring-2 focus:ring-blue-500' />
          </div>

          <button className='w-full bg-blue-500 text-white py-2 rounded-lg hover:bg-blue-600 transition duration-300'>
            Send Message
          </button>
        </form>

        <div className='mt-8 text-center'>
          <h2 className='text-lg font-semibold '>Contact Information</h2>
          <div className='flex flex-col items-center space-y-2 mt-4'>
            <div className='flex items-center '>
              <FaPhone  className="text-blue-500 mr-2"/>
              <span className='text-gray-500'>+9075754555</span>
            </div>

            <div className='flex items-center '>
              <FaEnvelope  className="text-blue-500 mr-2"/>
              <span className='text-gray-500'>WatoreVikas@gmail.com</span>
            </div>

             <div className='flex items-center '>
              <FaMapMarkedAlt   className="text-blue-500 mr-2"/>
              <span className='text-gray-500'>Pune</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Contact;