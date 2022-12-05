package UIElements.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

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

	public ProjectApplication(ActorRepository actorRepository, AddressRepository addressRepository,
							  CountryRepository countryRepository, FilmRepository filmRepository,
							  FilmActorRepository filmActorRepository){
		this.actorRepository = actorRepository;
		this.addressRepository = addressRepository;
		this.countryRepository = countryRepository;
		this.filmRepository = filmRepository;
		this.filmActorRepository = filmActorRepository;
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

	@PutMapping("/actors/{actorID}")
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

	@PostMapping("/actors")
	public Actor postActors(@RequestBody Actor newActor){
		return actorRepository.save(newActor);
	}

	@DeleteMapping("/actors/{actorID}")
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

	@GetMapping("/addresstest")
	public @ResponseBody Iterable<Object> matchAddressWithCountry(){
		return countryRepository.addressWithCountry();
	}

	@GetMapping("/films")
	public @ResponseBody Iterable<Film> getFilms(){
		return filmRepository.findAll();
	}

	@GetMapping("/actorsmatch")
	public @ResponseBody Iterable<Object> getFilmWithActor(){
		return actorRepository.matchFilm();
	}

	@GetMapping("/actorsfilms")
	public @ResponseBody Iterable<FilmActor> getFilmActor(){
		return filmActorRepository.findAll();
	}

}
