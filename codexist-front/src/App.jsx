import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import Swal from 'sweetalert2'
import { getPlaces } from './services/GetPlaces'
import {GoogleMap, InfoWindow, Marker, MarkerF, useLoadScript} from "@react-google-maps/api";
const App = ()=> {
  const [markers,setMarkers] = useState([]);
  const {isLoaded} = useLoadScript({
    googleMapsApiKey:"AIzaSyBfHOXW8c7QiN6_iJR6Hx5V7r8RYpnqBps",
  });
  const Search = async ()=>{
    let lat = document.getElementById("latitude").value.trim();
    let lon = document.getElementById("longitude").value.trim();
    let radius = document.getElementById("radius").value.trim();

    if((radius==null||radius=="")||(lat==null||lat=="")||(lon==null||lon=="")){
       Swal.fire({
          title:"You Should Fill All The Fields",
          icon:'error',
        });
    }
    else if(isNaN(radius)||isNaN(lat)||isNaN(lon)){
      Swal.fire({
        title:"All The Fields Should Be Number",
        icon:'error',
      });
    }
    else if(radius>20000){
        Swal.fire({
          title:"You Can Not Set Radius More Than 20000",
          icon:'error',
        });
    }
    else if(lat.split(".").length<=1){
      Swal.fire({
        title:"You Should Enter A Number With Dot For Latitude Field",
        icon:'error',
      });
    }
    else if(lon.split(".").length<=1){
      Swal.fire({
        title:"You Should Enter A Number With Dot For Longitude Field",
        icon:'error',
      });
    }
    else if(lat.split(".").length>2||lon.split(".").length>2){
      Swal.fire({
        title:"You Should Enter A Number With Just One Dot For Latitude And Longitude Fields",
        icon:'error',
      });
    }
    else {
        let splitedLat = lat.split(".");
        let splitedLon = lon.split(".");
        if(splitedLat[1].length>6||splitedLat[1].length<6){
          Swal.fire({
            title:"You Should Enter A Number With 6 Character After The Dot Like For Latitude 90.000000",
            icon:'error',
          });
        }
        else if(splitedLon[1].length>6||splitedLon[1].length<6){
          Swal.fire({
            title:"You Should Enter A Number With 6 Character After The Dot Like For Longitude 180.000000",
            icon:'error',
          });
        }
        else if(lat<-90||lat>90){
          Swal.fire({
            title:"Latitude Can Not be More Than 90 And Less Than -90",
            icon:'error',
          });
        }
        else if(lon<-180||lon>180){
          Swal.fire({
            title:"Longitude Can Not be More Than 180 And Less Than -180",
            icon:'error',
          });
        }
      else{
        let url = import.meta.env.VITE_REACT_APP_ROOT_URL+"/places";
       let response = await getPlaces(url,lat,lon,radius);
       setMarkers([]);
       for(let result of response.results.results){
        setMarkers(prewMarkers=>[...prewMarkers,{"name":result.name,"lat":result.geometry.location.lat,"lng":result.geometry.location.lng}])
       }
      }
    }
  }
  if(!isLoaded)return (<div>Loading....</div>)
  return (
    <>
    <h2 className='title'>Search Nearby Restaurants</h2>
    <div className='search'>
    <label htmlFor="latitude" className='labels'>Latitude:</label>
    <input type='text'id='latitude'name='latitude' className='texts'></input>
    <label htmlFor="longitude" className='labels'>Longitude:</label>
    <input type='text'id='longitude'name='longitude' className='texts'></input>
    <label htmlFor="radius" className='labels'>Radius:</label>
    <input type='text'id='radius'name='radius' className='texts'></input>
    </div>
    <button className='btn btn-success btnpos' id='search' onClick={Search}>Search</button> 
        <GoogleMap zoom={15} center={{lat:40.943001,lng:29.112930}} mapContainerClassName="map">
          {markers.map((marker,index)=>(
            <MarkerF InfoWindow={{content:"merhaba"}} animation={google.maps.Animation.DROP} position={{lat:marker.lat,lng:marker.lng}}label={{color:"black",text:marker.name,fontSize:"15px",className:"deneme"}}></MarkerF>
          ))}
        </GoogleMap>
    </>
  )
 
}


export default App
