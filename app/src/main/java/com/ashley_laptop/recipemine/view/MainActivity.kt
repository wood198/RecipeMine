package com.ashley_laptop.recipemine.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.ashley_laptop.recipemine.R
import com.ashley_laptop.recipemine.models.Search
import com.ashley_laptop.recipemine.viewmodel.ProfileViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.InputStream
import java.net.URL


class MainActivity : AppCompatActivity() {
    private lateinit var profileViewModel: ProfileViewModel
    private var user: String = ""
    private var isVeggies: Boolean = false
    private var isVegans: Boolean = false
    private var isGlutens: Boolean = false
    private var isDairys: Boolean = false
    private var intolerances: String = ""
    private var diets: String = ""

    private fun getUserName() = intent.extras?.get("username").toString().trim()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)


        user = getUserName()

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        profileViewModel.getUserProfileDairy("${user}Dairy").observe(this,
            androidx.lifecycle.Observer { dairyData(it) })
        profileViewModel.getUserProfileVeggie("${user}Veggie").observe(this,
            androidx.lifecycle.Observer { veggieData(it) })
        profileViewModel.getUserProfileVegan("${user}Vegan").observe(this,
            androidx.lifecycle.Observer { veganData(it) })
        profileViewModel.getUserProfileGluten("${user}Gluten").observe(this,
            androidx.lifecycle.Observer { glutenData(it) })

        val searchTask = asyncTaskSearch()
        val searchResults: Search? = searchTask.execute("number=8").get()

        val createUri1 =
            "https://spoonacular.com/recipeImages/" + searchResults?.mRecipes?.get(0)?.mId + "-240x150.jpg"
        val createUri2 =
            "https://spoonacular.com/recipeImages/" + searchResults?.mRecipes?.get(1)?.mId + "-240x150.jpg"
        val createUri3 =
            "https://spoonacular.com/recipeImages/" + searchResults?.mRecipes?.get(2)?.mId + "-240x150.jpg"
        val createUri4 =
            "https://spoonacular.com/recipeImages/" + searchResults?.mRecipes?.get(3)?.mId + "-240x150.jpg"
        val createUri5 =
            "https://spoonacular.com/recipeImages/" + searchResults?.mRecipes?.get(4)?.mId + "-240x150.jpg"
        val createUri6 =
            "https://spoonacular.com/recipeImages/" + searchResults?.mRecipes?.get(5)?.mId + "-240x150.jpg"
        val createUri7 =
            "https://spoonacular.com/recipeImages/" + searchResults?.mRecipes?.get(6)?.mId + "-240x150.jpg"
        val createUri8 =
            "https://spoonacular.com/recipeImages/" + searchResults?.mRecipes?.get(7)?.mId + "-240x150.jpg"

        var image: ImageView = findViewById(R.id.imgcard1)
        DownloadImageTask(image).execute(createUri1)
        var image2: ImageView = findViewById(R.id.imgcard2)
        DownloadImageTask(image2).execute(createUri2)
        var image3: ImageView = findViewById(R.id.imgcard3)
        DownloadImageTask(image3).execute(createUri3)
        var image4: ImageView = findViewById(R.id.imgcard4)
        DownloadImageTask(image4).execute(createUri4)
        var image5: ImageView = findViewById(R.id.imgcard5)
        DownloadImageTask(image5).execute(createUri5)
        var image6: ImageView = findViewById(R.id.imgcard6)
        DownloadImageTask(image6).execute(createUri6)
        var image7: ImageView = findViewById(R.id.imgcard7)
        DownloadImageTask(image7).execute(createUri7)
        var image8: ImageView = findViewById(R.id.imgcard8)
        DownloadImageTask(image8).execute(createUri8)

        val title1 = searchResults?.mRecipes?.get(0)?.mTitle
        val title2 = searchResults?.mRecipes?.get(1)?.mTitle
        val title3 = searchResults?.mRecipes?.get(2)?.mTitle
        val title4 = searchResults?.mRecipes?.get(3)?.mTitle
        val title5 = searchResults?.mRecipes?.get(4)?.mTitle
        val title6 = searchResults?.mRecipes?.get(5)?.mTitle
        val title7 = searchResults?.mRecipes?.get(6)?.mTitle
        val title8 = searchResults?.mRecipes?.get(7)?.mTitle

        recipeTitle1.text = title1
        recipeTitle2.text = title2
        recipeTitle3.text = title3
        recipeTitle4.text = title4
        recipeTitle5.text = title5
        recipeTitle6.text = title6
        recipeTitle7.text = title7
        recipeTitle8.text = title8

        row1.setOnClickListener {

            val url = searchResults?.mRecipes?.get(0)?.mSourceUrl

            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)

        }
        row2.setOnClickListener {

            val url = searchResults?.mRecipes?.get(1)?.mSourceUrl

            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)

        }
        row3.setOnClickListener {

            val url = searchResults?.mRecipes?.get(2)?.mSourceUrl

            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)

        }
        row4.setOnClickListener {

            val url = searchResults?.mRecipes?.get(3)?.mSourceUrl

            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)

        }
        row5.setOnClickListener {

            val url = searchResults?.mRecipes?.get(4)?.mSourceUrl

            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)

        }
        row6.setOnClickListener {

            val url = searchResults?.mRecipes?.get(5)?.mSourceUrl

            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)

        }
        row7.setOnClickListener {

            val url = searchResults?.mRecipes?.get(6)?.mSourceUrl

            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)

        }
        row8.setOnClickListener {

            val url = searchResults?.mRecipes?.get(7)?.mSourceUrl

            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)

        }

        cuisineRecipes.setOnClickListener {

            startActivity(Intent(this, RecipeActivity::class.java).apply {
                putExtra(
                    "username",
                    user
                )
            })

        }

        ingrediantRecipes.setOnClickListener {

            startActivity(Intent(this, IngredientActivity::class.java).apply {
                putExtra(
                    "username",
                    user
                )
            })

        }

        goToProfile.setOnClickListener {

            startActivity(Intent(this, ProfileActivity::class.java).apply {
                putExtra(
                    "username",
                    user
                )
            })

        }
    }


    override fun onBackPressed() { // do nothing.
    }

    private class DownloadImageTask(var bmImage: ImageView) :
        AsyncTask<String?, Void?, Bitmap?>() {
        protected override fun doInBackground(vararg urls: String?): Bitmap? {
            val urldisplay = urls[0]
            var mIcon11: Bitmap? = null
            try {
                val inputStream: InputStream = URL(urldisplay).openStream()
                mIcon11 = BitmapFactory.decodeStream(inputStream)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return mIcon11
        }

        override fun onPostExecute(result: Bitmap?) {
            bmImage.setImageBitmap(result)
        }
    }

    class asyncTaskSearch() : AsyncTask<String?, Void, Search?>() {
        override fun doInBackground(vararg params: String?): Search? {

            try {
                val client = OkHttpClient()

                val request: Request = Request.Builder()
                    .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/random?" + params.get(0))
                    .get()
                    .addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", PUT_KEY_HERE)
                    .build()

                val response: Response = client.newCall(request).execute()

                val responseJSON:String = response.body().string()
//                Log.d("TEMP", responseJSON)

                val searchResults: Search = Gson().fromJson(responseJSON, Search::class.java)
                return searchResults
            }
            catch (ex:Exception) { }

            return null
        }

        override fun onPreExecute() { super.onPreExecute() }
        override fun onPostExecute(result: Search?) { super.onPostExecute(result) }
    }

    private fun dairyData(dairy: Boolean){
        isDairys = dairy
        if(isDairys){
            intolerances += "dairy, "
        }
    }

    private fun veggieData(veggie: Boolean){
        isVeggies = veggie
        if(isVeggies){
            diets += "vegetarian, "
        }
    }

    private fun veganData(vegan: Boolean){
        isVegans = vegan
        if(isVegans){
            diets += "vegan"
        }
    }

    private fun glutenData(gluten: Boolean){
        isGlutens = gluten
        if(isGlutens){
            intolerances += "gluten"
        }
    }
}
