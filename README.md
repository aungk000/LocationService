# LocationService
This class can generate UTM, MGRS values and can listen on location changes for ease of use.

Example
-------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        
        locationService = new LocationService(this, requestCode, LocationService.OnLocationChangedListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        locationService.stop();
    }

    @OnClick(R.id.btn_request_location)
    public void onViewClicked() 
    {
        locationService.requestLocation();
    }

    @Override
    public void onLocationChanged(Location location, String utm, String mgrs)
    {
        // Update UI here
    }

Screenshot
----------
![Screenshot-1](/screenshot/screenshot-1.jpg)
![Screenshot-2](/screenshot/screenshot-2.jpg)
![Screenshot-3](/screenshot/screenshot-3.jpg)