package com.example.dardan.elearning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public static ArrayList<Category> categories;
    CustomCategoryAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        categories = new ArrayList<>();
        populateCategoriesList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateHighscores();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, QuizActivity.class);
        switch (item.getItemId()) {
            case R.id.quiz_fruits:
                intent.putExtra("position", 0);
                startActivity(intent);
                return true;
            case R.id.quiz_animals:
                intent.putExtra("position", 1);
                startActivity(intent);
                return true;
            case R.id.quiz_food:
                intent.putExtra("position", 2);
                startActivity(intent);
                return true;
            case R.id.quiz_colors:
                intent.putExtra("position", 3);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void populateCategoriesList() {
        Highscores.open(this);
        Category fruitCategory = new Category("Fruits",
                R.drawable.fruits,
                Highscores.getHighscore(MySQLiteHelper.COLUMN_FRUITS),
                getResources().getColor(R.color.primary_dark),
                R.style.GreenTheme,
                MySQLiteHelper.COLUMN_FRUITS,
                R.drawable.ic_face_green);
        Category animalCategory = new Category("Animals",
                R.drawable.animals,
                Highscores.getHighscore(MySQLiteHelper.COLUMN_ANIMALS),
                getResources().getColor(R.color.blue_primary_dark),
                R.style.BlueTheme,
                MySQLiteHelper.COLUMN_ANIMALS,
                R.drawable.ic_face_blue);
        Category foodCategory = new Category("Food",
                R.drawable.food,
                Highscores.getHighscore(MySQLiteHelper.COLUMN_FOOD),
                getResources().getColor(R.color.pink_primary_dark),
                R.style.PinkTheme,
                MySQLiteHelper.COLUMN_FOOD,
                R.drawable.ic_face_pink);
        Category colorsCategory = new Category("Colors",
                R.drawable.colors,
                Highscores.getHighscore(MySQLiteHelper.COLUMN_COLORS),
                getResources().getColor(R.color.purple_primary_dark),
                R.style.PurpleTheme,
                MySQLiteHelper.COLUMN_COLORS,
                R.drawable.ic_face_purple);
        Highscores.close();

        fruitCategory.addThing(new Thing(R.drawable.apple, R.raw.apple, "Apple"));
        fruitCategory.addThing(new Thing(R.drawable.orange, R.raw.orange, "Orange"));
        fruitCategory.addThing(new Thing(R.drawable.banana, R.raw.banana, "Banana"));
        fruitCategory.addThing(new Thing(R.drawable.cherry, R.raw.cherry, "Cherry"));
        fruitCategory.addThing(new Thing(R.drawable.dates, R.raw.dates, "Dates"));
        fruitCategory.addThing(new Thing(R.drawable.coconut, R.raw.coconut, "Coconut"));
        fruitCategory.addThing(new Thing(R.drawable.grape, R.raw.grape, "Grape"));
        fruitCategory.addThing(new Thing(R.drawable.kiwi, R.raw.kiwi, "Kiwi"));
        fruitCategory.addThing(new Thing(R.drawable.lemon, R.raw.lemon, "Lemon"));
        fruitCategory.addThing(new Thing(R.drawable.peach, R.raw.peach, "Peach"));
        fruitCategory.addThing(new Thing(R.drawable.pear, R.raw.pear, "Pear"));
        fruitCategory.addThing(new Thing(R.drawable.persimmon, R.raw.persimmon, "Persimmon"));
        fruitCategory.addThing(new Thing(R.drawable.pineapple, R.raw.pineapple, "Pineapple"));
        fruitCategory.addThing(new Thing(R.drawable.plum, R.raw.plum, "Plum"));
        fruitCategory.addThing(new Thing(R.drawable.raspberry, R.raw.raspberry, "Raspberry"));
        fruitCategory.addThing(new Thing(R.drawable.strawberry, R.raw.strawberry, "Strawberry"));
        fruitCategory.addThing(new Thing(R.drawable.watermelon, R.raw.watermelon, "Watermelon"));
        fruitCategory.addThing(new Thing(R.drawable.mango, R.raw.mango, "Mango"));

        animalCategory.addThing(new Thing(R.drawable.dog, R.raw.dog, "Dog", R.raw.dognoise));
        animalCategory.addThing(new Thing(R.drawable.bear, R.raw.bear, "Bear", R.raw.bearnoise));
        animalCategory.addThing(new Thing(R.drawable.wolf, R.raw.wolf, "Wolf", R.raw.wolfnoise));
        animalCategory.addThing(new Thing(R.drawable.dolphin, R.raw.dolphin, "Dolphin", R.raw.dolphinnoise));
        animalCategory.addThing(new Thing(R.drawable.duck, R.raw.duck, "Duck", R.raw.ducknoise));
        animalCategory.addThing(new Thing(R.drawable.leopard, R.raw.leopard, "Leopard", R.raw.leopardnoise));
        animalCategory.addThing(new Thing(R.drawable.lion, R.raw.lion, "Lion", R.raw.lionnoise));
        animalCategory.addThing(new Thing(R.drawable.monkey, R.raw.monkey, "Monkey", R.raw.monkeynoise));
        animalCategory.addThing(new Thing(R.drawable.penguin, R.raw.penguin, "Penguin", R.raw.penguinnoise));
        animalCategory.addThing(new Thing(R.drawable.rooster, R.raw.rooster, "Rooster", R.raw.roosternoise));
        animalCategory.addThing(new Thing(R.drawable.sheep, R.raw.sheep, "Sheep", R.raw.sheepnoise));
        animalCategory.addThing(new Thing(R.drawable.snake, R.raw.snake, "Snake", R.raw.snakenoise));
        animalCategory.addThing(new Thing(R.drawable.tiger, R.raw.tiger, "Tiger", R.raw.tigernoise));
        animalCategory.addThing(new Thing(R.drawable.fox, R.raw.fox, "Fox", R.raw.foxnoise));
        animalCategory.addThing(new Thing(R.drawable.camel, R.raw.camel, "Camel", R.raw.camelnoise));

        foodCategory.addThing(new Thing(R.drawable.bread, R.raw.bread, "Bread"));
        foodCategory.addThing(new Thing(R.drawable.burger, R.raw.burger, "Burger"));
        foodCategory.addThing(new Thing(R.drawable.cheese, R.raw.cheese, "Cheese"));
        foodCategory.addThing(new Thing(R.drawable.chocolate, R.raw.chocolate, "Chocolate"));
        foodCategory.addThing(new Thing(R.drawable.coffee, R.raw.coffee, "Coffee"));
        foodCategory.addThing(new Thing(R.drawable.egg, R.raw.egg, "Egg"));
        foodCategory.addThing(new Thing(R.drawable.honey, R.raw.honey, "Honey"));
        foodCategory.addThing(new Thing(R.drawable.hotdog, R.raw.hotdog, "Hot Dog"));
        foodCategory.addThing(new Thing(R.drawable.icecream, R.raw.icecream, "Ice Cream"));
        foodCategory.addThing(new Thing(R.drawable.meat, R.raw.meat, "Meat"));
        foodCategory.addThing(new Thing(R.drawable.pizza, R.raw.pizza, "Pizza"));
        foodCategory.addThing(new Thing(R.drawable.sandwich, R.raw.sandwich, "Sandwich"));
        foodCategory.addThing(new Thing(R.drawable.sausage, R.raw.sausage, "Sausage"));
        foodCategory.addThing(new Thing(R.drawable.water, R.raw.water, "Water"));

        colorsCategory.addThing(new Thing(R.drawable.blue, R.raw.blue, "Blue"));
        colorsCategory.addThing(new Thing(R.drawable.pink, R.raw.pink, "Pink"));
        colorsCategory.addThing(new Thing(R.drawable.green, R.raw.green, "Green"));
        colorsCategory.addThing(new Thing(R.drawable.orange_color, R.raw.orange_color, "Orange"));
        colorsCategory.addThing(new Thing(R.drawable.purple, R.raw.purple, "Purple"));
        colorsCategory.addThing(new Thing(R.drawable.red, R.raw.red, "Red"));
        colorsCategory.addThing(new Thing(R.drawable.yellow, R.raw.yellow, "Yellow"));
        colorsCategory.addThing(new Thing(R.drawable.brown, R.raw.brown, "Brown"));
        colorsCategory.addThing(new Thing(R.drawable.gray, R.raw.gray, "Gray"));
        colorsCategory.addThing(new Thing(R.drawable.white, R.raw.white, "White"));
        colorsCategory.addThing(new Thing(R.drawable.black, R.raw.black, "Black"));

        categories.add(fruitCategory);
        categories.add(animalCategory);
        categories.add(foodCategory);
        categories.add(colorsCategory);

        // Create the adapter to convert the array to views
        adapter = new CustomCategoryAdapter(this, categories);
        // Attach the adapter to a ListView
        listView = (ListView) findViewById(R.id.listViewCards);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private void updateHighscores() {
        Highscores.open(this);
        for (Category c : categories) {
            c.updateHighscore();
        }
        Highscores.close();
        // notifies the adapter to display the latest Highscores
        // actually this method calls getView from CustomCategoryAdapter class
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ThingsActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}
