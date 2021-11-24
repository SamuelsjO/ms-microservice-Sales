require('dotenv').config();

module.exports = {
  type: process.env.PGDB_TYPE,
  host: process.env.PGHOST,
  port: process.env.POSTGRES_PORT,
  username: process.env.POSTGRES_USER,
  password: process.env.POSTGRES_PASSWORD,
  database: process.env.POSTGRES_DB,
  synchronize: true,
  logging: false,
  entities: [process.env.DB_ENTITIES || './dist/models/*.js'],
  migrations: [process.env.DB_MIGRATIONS || './dist/database/migrations/**/*.js'],
  migrationsRun: true,
  cli: {
    migrationsDir: './src/database/migrations',
  },
};
