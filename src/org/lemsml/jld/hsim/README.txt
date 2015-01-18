
The hsim (hierarchical simulation) package maps LEMS models onto objects from the hrun package which provides a direct, but slow 
implementation of LEMS. Each component is mapped to a StateType object which spawns StateInstance objects to hold the state of 
the model and perform fixed timestep updates of the state through time. 