import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import {GoogleMap, InfoWindow, Marker, MarkerF, useLoadScript} from "@react-google-maps/api";
function App() {
  //const [count, setCount] = useState(0)
 
  const {isLoaded} = useLoadScript({
    googleMapsApiKey:"AIzaSyBfHOXW8c7QiN6_iJR6Hx5V7r8RYpnqBps",
  });
  useEffect(()=>{
    navigator.geolocation.getCurrentPosition((position)=>{
      console.log(position);
      //setLat(position.coords.latitude);
      //setLon(position.coords.longitude);
    })
    //console.log("latitude : "+lat);
    //console.log("longitude : "+lon);
  });
  if(!isLoaded)return (<div>Loading....</div>)
  return (
    <>
    <h2 className='title'>Search Nearby Restaurants</h2>
    <div className='search'>
    <label for="latitude" className='labels'>Latitude:</label>
    <input type='text'id='latitude'name='latitude' className='texts'></input>
    <label for="longitude" className='labels'>Longitude:</label>
    <input type='text'id='longitude'name='longitude' className='texts'></input>
    <label for="radius" className='labels'>Radius:</label>
    <input type='text'id='radius'name='radius' className='texts'></input>
    </div>
    <button className='btn btn-success btnpos'>deneme</button>
    
        <GoogleMap zoom={10} center={{lat:43,lng:29}} mapContainerClassName="map">
          <MarkerF InfoWindow={{content:"merhaba"}} position={{lat:44,lng:-80}}label={{color:"black",text:"HacıOğlu Lahmacun Kebap",fontSize:"15px",className:"deneme"}}></MarkerF>
        </GoogleMap>
       
    </>
  )
}

export default App
