rsconf = {_id: "rs0", members: [{_id: 0, host: "localhost:27017"}]};
rs.initiate(rsconf);
rs.add("localhost:27018");
rs.add("localhost:27019");
rs.add("localhost:27020");