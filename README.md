# LocationService
This class can generate UTM, MGRS values and can listen on location changes for ease of use.

Example
-------
public class LocationActivity extends AppCompatActivity implements LocationService.OnLocationChangedListener
{
    private LocationService locationService;
    private final int REQUEST_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        

        locationService = new LocationService(this, REQUEST_LOCATION, this);
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
}

