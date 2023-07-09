
export const getPlaces = async(url,lat,lon,radius)=>{
    let response = await fetch(url+"?lat="+lat+"&lon="+lon+"&radius"+radius);
    
}