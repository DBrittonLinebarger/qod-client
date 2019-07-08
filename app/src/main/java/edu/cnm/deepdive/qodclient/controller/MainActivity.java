package edu.cnm.deepdive.qodclient.controller;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import edu.cnm.deepdive.qodclient.R;
import edu.cnm.deepdive.qodclient.model.Quote;
import edu.cnm.deepdive.qodclient.service.QodService;
import edu.cnm.deepdive.qodclient.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

  private MainViewModel viewModel;
  private LiveData<Quote> randomQuote;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setupToolbar();
    setupFab();
    setupViewModel();
    //setupButton();
    Button button = (Button)findViewById(R.id.search_button);

    button.setOnClickListener(
        new Button.OnClickListener() {
          public void onClick(View view) {
            ListView list_view1 =
                (ListView)findViewById(R.id.list_view1);

          }
        }
    );


  }

  private void setupViewModel() {
    View rootView = findViewById(R.id.root_view);
    viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    viewModel.getRandomQuote().observe(this, (quote) -> {
      AlertDialog dialog = new AlertDialog.Builder(this)
          .setTitle("Random Quote") // TODO Extract to resource.
          .setMessage(quote.getText() + quote.getCombinedSources())
          .setPositiveButton("Cool!", (dialogInterface, i) -> {})
          .create();
      dialog.show();
    });
    // Note: the placement of this observer might be wrong; the
    // Does a search argument need to be passed to getRequestResult()?;
    // and perhaps a QuoteList class needs to be defined?

    //viewModel.getRequestResult().observe(this, (quoteList) -> {
    //  AlertDialog dialog = new AlertDialog.Builder(this)
    //      .setTitle("Quote List")
    //      .setMessage(quoteList.getText() + quoteList.getCombinedSources())
    //      .setPositiveButton("Cool!", (dialogInterface, i) -> {})
    //      .create();
    //  dialog.show();
    //});

  }

  private void setupFab() {
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(view -> viewModel.getRandomQuote());
  }

  private void setupToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }

  //private void setupButton() {
  //  Button button = (Button)findViewById(R.id.search_button);
  //  button.setOnClickListener(view -> viewModel.getRequestResult());
  //}

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //no inspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
