package com.ziooo.dao;

import java.util.List;

import com.ziooo.model.Pause;
import com.ziooo.model.Person;
import com.ziooo.model.Type;

public interface PauseDAO {

	Pause createPause(String date, String time, double gain, Person person, Type type);

	List<Pause> getAllPauses();

	Pause getPauseById(long idPause);

	int updatePause(Pause pause);

	int deletePause(Pause pause);

}
