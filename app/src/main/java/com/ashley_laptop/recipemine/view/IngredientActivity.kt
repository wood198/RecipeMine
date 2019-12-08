package com.ashley_laptop.recipemine.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.ashley_laptop.recipemine.R
import com.ashley_laptop.recipemine.models.Recipe
import com.google.gson.Gson
import com.ashley_laptop.recipemine.viewmodel.ProfileViewModel
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_ingrediant.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.InputStream
import java.lang.reflect.Type
import java.net.URL


class IngredientActivity : AppCompatActivity() {
    private lateinit var profileViewModel: ProfileViewModel
    private var user: String = ""
    private var isVeggies: Boolean = false
    private var isVegans: Boolean = false
    private var isGlutens: Boolean = false
    private var isDairys: Boolean = false
    private var intolerances: String = ""
    private var diets: String = ""
    private var ingredients: String = ""

    private fun getUserName() = intent.extras?.get("username").toString().trim()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingrediant)

        user = getUserName()

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        profileViewModel.getUserProfileDairy("${user}Dairy").observe(this,
            androidx.lifecycle.Observer {dairyData(it)})
        profileViewModel.getUserProfileVeggie("${user}Veggie").observe(this,
            androidx.lifecycle.Observer {veggieData(it)})
        profileViewModel.getUserProfileVegan("${user}Vegan").observe(this,
            androidx.lifecycle.Observer {veganData(it)})
        profileViewModel.getUserProfileGluten("${user}Gluten").observe(this,
            androidx.lifecycle.Observer {glutenData(it)})

        enterIngredient.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })


        enterButtonIngredient.setOnClickListener {

            ingredients = enterIngredient.text.toString()

            val searchTask = asyncTaskSearch()
            val searchResults:List<Recipe>? = searchTask.execute("ingredients=${ingredients}&number=8&ingredients=${ingredients}").get()
            Log.d("TEMP", searchResults?.get(0)?.mTitle)

            val createUri1 = "https://spoonacular.com/recipeImages/" + searchResults?.get(0)?.mId + "-240x150.jpg"
            val createUri2 = "https://spoonacular.com/recipeImages/" + searchResults?.get(1)?.mId + "-240x150.jpg"
            val createUri3 = "https://spoonacular.com/recipeImages/" + searchResults?.get(2)?.mId + "-240x150.jpg"
            val createUri4 = "https://spoonacular.com/recipeImages/" + searchResults?.get(3)?.mId + "-240x150.jpg"
            val createUri5 = "https://spoonacular.com/recipeImages/" + searchResults?.get(4)?.mId + "-240x150.jpg"
            val createUri6 = "https://spoonacular.com/recipeImages/" + searchResults?.get(5)?.mId + "-240x150.jpg"
            val createUri7 = "https://spoonacular.com/recipeImages/" + searchResults?.get(6)?.mId + "-240x150.jpg"
            val createUri8 = "https://spoonacular.com/recipeImages/" + searchResults?.get(7)?.mId + "-240x150.jpg"

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

        }

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

    class asyncTaskSearch() : AsyncTask<String?, Void, List<Recipe>?>() {
        override fun doInBackground(vararg params: String?): List<Recipe>? {

            try {
                val client = OkHttpClient()

                val request: Request = Request.Builder()
                    .url("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/findByIngredients?" + params.get(0))
                    .get()
                    .addHeader("x-rapidapi-host", "spoonacular-recipe-food-nutrition-v1.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "526f7ebcf2msh61e773bb3c87effp12ca72jsn7c53ce0c2887")
                    .build()

                val response: Response = client.newCall(request).execute()

                val responseJSON:String = response.body().string()
//                Log.d("TEMP", responseJSON)

                val type: Type = object : TypeToken<List<Recipe>>() {}.type

                val ingSearch: List<Recipe> = Gson().fromJson<List<Recipe>>(responseJSON, type)
                Log.d("TMP", ingSearch.get(0).mTitle)

                //val searchResults: Search = Gson().fromJson(responseJSON, Search::class.java)
                return ingSearch
            }
            catch (ex:Exception) { }

            return null
        }

        override fun onPreExecute() { super.onPreExecute() }
        override fun onPostExecute(result: List<Recipe>?) { super.onPostExecute(result) }
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
