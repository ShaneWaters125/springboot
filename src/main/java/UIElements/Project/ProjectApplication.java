package UIElements.Project;

import UIElements.Project.ReturnInterfaces.CategoryWithFilmInterface;
import UIElements.Project.ReturnInterfaces.FilmWithActorInterface;
import UIElements.Project.ReturnInterfaces.FilmsWithActorInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/home")
@CrossOrigin
public class ProjectApplication {

	//http://localhost:8080/home/getactor/21
	@Autowired
	private ActorRepository actorRepository;
	private AddressRepository addressRepository;
	private CountryRepository countryRepository;
	private FilmRepository filmRepository;

	private FilmActorRepository filmActorRepository;

	private FilmCategoryRepository filmCategoryRepository;

	private CategoryRepository categoryRepository;

	private LanguageRepository languageRepository;

	public ProjectApplication(ActorRepository actorRepository, FilmRepository filmRepository,
							  FilmActorRepository filmActorRepository, FilmCategoryRepository filmCategoryRepository){
		this.actorRepository = actorRepository;
//		this.addressRepository = addressRepository;
//		this.countryRepository = countryRepository;
		this.filmRepository = filmRepository;
		this.filmActorRepository = filmActorRepository;
		this.filmCategoryRepository = filmCategoryRepository;
//		this.categoryRepository = categoryRepository;
//		this.languageRepository = languageRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@GetMapping("/actors")
	public @ResponseBody Iterable<Actor> getActors(){
		return actorRepository.findAll();
	}

	@GetMapping("/actors/{actorID}")
	public Actor getActorsByID(@PathVariable int actorID){
		return actorRepository.findById(actorID)
				.orElseThrow(() -> new ResourceAccessException("Actor with ID: " + actorID + " does not exist."));
	}

	@PutMapping("/actors/update/{actorID}")
	public Actor putActorsByID(@RequestBody Actor newActor, @PathVariable int actorID){
		return actorRepository.findById(actorID)
				.map(actor -> {
					actor.setFirstName(newActor.getFirstName());
					actor.setLastName(newActor.getLastName());
					return actorRepository.save(actor);
				})
				.orElseGet(() -> {
					newActor.setActorId(actorID);
					return actorRepository.save(newActor);
				});
	}

	@PostMapping("/actors/add")
	public Actor postActors(@RequestBody Actor newActor){
		return actorRepository.save(newActor);
	}

	@DeleteMapping("/actors/delete/{actorID}")
	public void deleteActorsByID(@PathVariable int actorID){
		actorRepository.deleteById(actorID);
	}

	@GetMapping("/actors/first")
	public @ResponseBody Actor getFirstActor(){
		return actorRepository.findFirst();
	}

	@GetMapping("/actors/last")
	public @ResponseBody Actor getLastActor(){
		return actorRepository.findLast();
	}

	@GetMapping("/address")
	public @ResponseBody Iterable<Address> getAddress(){
		return addressRepository.findAll();
	}

	@GetMapping("/actors/{actorid}/films")
	public @ResponseBody Iterable<FilmsWithActorInterface> getFilmWithActor(@PathVariable int actorid){
		return actorRepository.getFilmsWithActor(actorid);
	}

	@GetMapping("/films")
	public @ResponseBody Iterable<Film> getFilms(){
		return filmRepository.findAll();
	}

	@GetMapping("/films/id/{filmID}")
	public Film getFilmsById (@PathVariable int filmID){
		return filmRepository.findById(filmID)
				.orElseThrow(() -> new ResourceAccessException("Film with ID: " + filmID + " does not exist."));
	}

	@GetMapping("/films/category")
	public @ResponseBody Iterable<FilmCategory> getFilmCategory(){
		return filmCategoryRepository.findAll();
	}

	@GetMapping("/films/category/{category}")
	public @ResponseBody Iterable<Film> matchCategoryWithFilms(@PathVariable String category){
		return filmRepository.matchCategoryWithFilms(category);
	}

	@GetMapping("/films/{filmid}/actors")
	public @ResponseBody Iterable<FilmWithActorInterface> matchFilmWithActors(@PathVariable int filmid){
		return filmRepository.matchFilmWithActors(filmid);
	}

	@GetMapping("/films/name/{filmname}")
	public @ResponseBody Iterable<Film> findFilmWithName(@PathVariable String filmname) {
		return filmRepository.findFilmWithName(filmname);
	}

	@GetMapping("/films/description/{filmdesc}")
	public @ResponseBody Iterable<Film> findFilmWithDescription(@PathVariable String filmdesc) {
		return filmRepository.findFilmWithDescription(filmdesc);
	}

	@GetMapping("/actors/name/{actorfirstname}")
	public @ResponseBody Iterable<Actor> findActorWithName(@PathVariable String actorfirstname){
		return actorRepository.findActorWithName(actorfirstname, "");
	}

	@GetMapping("/actors/name/{actorfirstname}/{actorlastname}")
	public @ResponseBody Iterable<Actor> findActorWithName(@PathVariable String actorfirstname, @PathVariable String actorlastname){
		return actorRepository.findActorWithName(actorfirstname, actorlastname);
	}

	@GetMapping("films/length/{filmlength}")
	public @ResponseBody Iterable<Film> findFilmsWithLessLengthThan(@PathVariable int filmlength){
		return filmRepository.findFilmsWithLessLengthThan(filmlength);
	}

	@GetMapping("films/rating/{filmrating}")
	public @ResponseBody Iterable<Film> findFilmsWithRating(@PathVariable String filmrating){
		return filmRepository.findFilmsWithRating(filmrating);
	}

	@PostMapping("/films/{filmid}/add/{actorid}")
	public FilmActor addActorToFilm(@PathVariable int filmid, @PathVariable int actorid){
		Film selfilm = filmRepository.findById(filmid).orElseThrow(() -> new ResourceAccessException("Film with ID: " + filmid + " does not exist."));
		Actor selfactor = actorRepository.findById(actorid).orElseThrow(() -> new ResourceAccessException("Actor with ID: " + actorid + " does not exist."));
		return filmActorRepository.save(new FilmActor(selfactor, selfilm));
	}

	@GetMapping("/films/random")
	public @ResponseBody Film getRandomFilm(){
		List<Film> films = filmRepository.findAll();
		int rand = (int) (Math.random()*films.size()+1);
		return films.get(rand);
	}

	@PostMapping("/films/add")
	public void addFilm(@RequestBody Film newFilm){
		filmRepository.save(newFilm);
	}

}
