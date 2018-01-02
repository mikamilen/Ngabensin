
package com.example.root.ngabensin.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("geocoded_waypoints")
    @Expose
    private List<GeocodedWaypoint> geocodedWaypoints = null;
    @SerializedName("routes")
    @Expose
    private List<Route> routes = null;
    @SerializedName("status")
    @Expose
    private String status;

    public List<GeocodedWaypoint> getGeocodedWaypoints() {
        return geocodedWaypoints;
    }

    public void setGeocodedWaypoints(List<GeocodedWaypoint> geocodedWaypoints) {
        this.geocodedWaypoints = geocodedWaypoints;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    class GeocodedWaypoint {

        @SerializedName("geocoder_status")
        @Expose
        private String geocoderStatus;
        @SerializedName("place_id")
        @Expose
        private String placeId;
        @SerializedName("types")
        @Expose
        private List<String> types = null;

        public String getGeocoderStatus() {
            return geocoderStatus;
        }

        public void setGeocoderStatus(String geocoderStatus) {
            this.geocoderStatus = geocoderStatus;
        }

        public String getPlaceId() {
            return placeId;
        }

        public void setPlaceId(String placeId) {
            this.placeId = placeId;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

    }

}
